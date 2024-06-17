package com.busanit501.boot501.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {
    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Board board;


    @Override
    public int compareTo(BoardImage other) {
        // 현재 이미지와, 매개변수로 넘어온 이미지의 ord를 비교를 해서 정렬
        // 양수는 , 오름차순, 음수 내림차순.
        return this.ord - other.ord;
    }
}
