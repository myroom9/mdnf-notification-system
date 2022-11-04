package com.mdnf.mdnf_notification_system.type;

import lombok.Getter;

@Getter
public enum BoardType {
    NOTICE("notice", "\uD83E\uDD20 NEW 공지사항 발생 \uD83E\uDD20\n제목: %s\nURL: https://dnfm.nexon.com/News/Notice/View/%s", "던파모바일 공지사항"),
    DEV_NOTE("dev-note", "\uD83E\uDD20 NEW 개발자노트 발생 \uD83E\uDD20\n제목: %s\nURL: https://dnfm.nexon.com/News/Devnote/View/%s" ,"던파모바일 개발자노트"),
    ;

    private final String boardType;
    private final String content;
    private final String description;

    BoardType(String boardType, String content, String description) {
        this.boardType = boardType;
        this.content = content;
        this.description = description;
    }
}
