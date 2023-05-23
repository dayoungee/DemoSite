package com.web.demo.chat.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.web.demo.chat.domain.QChatRoom.chatRoom;

@RequiredArgsConstructor
public class ChatCustomRepositoryImpl implements ChatCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int plusUserCnt(Long roomId) {
        return (int) jpaQueryFactory.update(chatRoom)
                .set(chatRoom.userCount, chatRoom.userCount.add(1))
                .where(chatRoom.id.eq(roomId)).execute();
    }
}
