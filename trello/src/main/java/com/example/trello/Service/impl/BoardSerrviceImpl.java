package com.example.trello.Service.impl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.trello.Dto.Request.BoardRequest;
import com.example.trello.Entity.BoardEntity;
import com.example.trello.Exception.ForbiddenErrorException;
import com.example.trello.Repository.BoardRepository;
import com.example.trello.Service.BoardService;
import com.example.trello.Util.Util;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BoardSerrviceImpl implements BoardService {
    BoardRepository boardRepository;

    public BoardEntity createBoard(BoardRequest.createBoard payload) {
        String userId = Util.getIdByToken();

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(payload.getTitle());

        boardEntity.setStatus(payload.getStatus());

        boardEntity.setCreatedBy(UUID.fromString(userId));
        boardEntity.setBackground(payload.getBackground());
        boardEntity.setPermissionViewer(payload.getPermissionViewer());

        List<UUID> ownerIds = new ArrayList<>();
        List<UUID> memberIds = new ArrayList<>();
        List<UUID> columnOrders = new ArrayList<>();
        memberIds.add(UUID.fromString(userId));

        boardEntity.setOwnerIds(ownerIds);
        boardEntity.setMemberIds(memberIds);
        boardEntity.setColumnOrders(columnOrders);

        boardRepository.save(boardEntity);

        return boardEntity;
    }

    public String deleteBoard(BoardRequest.deleteBoard payload) {

        String userId = Util.getIdByToken();
        BoardEntity findBoard = boardRepository.findById(UUID.fromString(payload.getId())).orElse(null);
        if (findBoard == null) {
            throw new ForbiddenErrorException("Board không tồn tại");
        }

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setIsDelete(true);
        boardEntity.setUpdatedAt(new Date());
        boardEntity.setUpdatedBy(UUID.fromString(userId));

        boardRepository.save(boardEntity);

        return "delete board success";
    }

    public Map<String, Object> getBoard(BoardRequest.getBoard payload) {

        Integer limit = payload.getLimit() == null ? 10 : payload.getLimit();
        Integer start = payload.getStart() == null ? 0 : payload.getStart();

        String sortField = payload.getSortField() == null ? "createdAt" : payload.getSortField();
        String sortType = payload.getSortType() == null ? "desc" : payload.getSortType();
        Sort configSort = sort(sortField, sortType);
        Pageable paging = PageRequest.of(start, limit, configSort);

        Specification<BoardEntity> spec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            Set<String> date = new HashSet();
            date.add("updatedAt");
            date.add("createdAt");

            predicates.add(criteriaBuilder.notEqual(root.get("isDelete"), true));

            for (BoardRequest.condition condition : payload.getConditions()) {
                Path<String> key = root.get(condition.getKey());
                List<Object> valueCondition = condition.getValue();

                // date
                if (date.contains(key)) {
                    if (valueCondition.size() == 2) {
                        OffsetDateTime startDate = OffsetDateTime.parse((CharSequence) valueCondition.get(0),
                                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        OffsetDateTime endDate = OffsetDateTime.parse((CharSequence) valueCondition.get(1),
                                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        predicates.add(criteriaBuilder.between(key.as(OffsetDateTime.class), startDate, endDate));
                    }
                }
                // boolean
                else if (Boolean.class.equals(key.getJavaType())) {
                    predicates.add(criteriaBuilder.equal(key, valueCondition.get(0)));
                }
                // list
                else if (key.equals("memberIds") || key.equals("ownerIds")) {
                    for (Object item : valueCondition) {

                    }
                } // other
                else {
                    predicates.add(criteriaBuilder.like(key, "%" + valueCondition.get(0) + "%"));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<BoardEntity> response = boardRepository.findAll(spec, paging);

        Map<String, Object> result = new HashMap<>();
        result.put("record", response.getContent());
        result.put("totalPage", response.getTotalPages());
        result.put("size", response.getSize());
        result.put("totalElement", response.getTotalElements());

        return result;
    }

    private Sort sort(String sortField, String sortType) {
        return sortType == "asc" ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    public BoardEntity getDetailBoard(BoardRequest.getDetail payload) {
        BoardEntity findBoard = boardRepository.findById(UUID.fromString(payload.getId())).orElse(null);
        if (findBoard == null) {
            throw new ForbiddenErrorException("Board không tồn tại");
        }
        return findBoard;
    }
}