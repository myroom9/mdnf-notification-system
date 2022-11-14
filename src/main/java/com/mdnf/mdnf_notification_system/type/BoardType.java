package com.mdnf.mdnf_notification_system.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BoardType {
    NOTICE("notice", "\uD83D\uDCE2 NEW 공지사항 발생 \uD83D\uDCE2\n제목: %s\nURL: https://dnfm.nexon.com/News/Notice/View/%s", "던파모바일 공지사항"),
    DEV_NOTE("dev-note", "\uD83D\uDCBB NEW 개발자노트 발생 \uD83D\uDCBB\n제목: %s\nURL: https://dnfm.nexon.com/News/Devnote/View/%s" ,"던파모바일 개발자노트"),
    UPDATE("update", "\uD83C\uDD99 NEW 업데이트 발생 \uD83C\uDD99\n제목: %s\nURL: https://dnfm.nexon.com/News/Update/View/%s" ,"던파모바일 업데이트"),
    ;

    private final String boardType;
    private final String content;
    private final String description;

    BoardType(String boardType, String content, String description) {
        this.boardType = boardType;
        this.content = content;
        this.description = description;
    }

    public static BoardType of(String boardType) {
        return Arrays.stream(values())
                .filter(o -> o.getBoardType().equals(boardType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 boardType입니다. " + boardType));
    }
}
