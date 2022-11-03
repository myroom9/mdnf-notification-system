package com.mdnf.mdnf_notification_system.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;

public class MdnfResponse {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Notice {
        public ArrayList<Content> threads;
        public int totalElements;
        public int totalPages;
        public int numberOfElements;
        public ArrayList<String> blockStartKey;
        public int blockStartNo;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content {
        public int threadId;
        public int communityId;
        public int boardId;
        public int headlineId;
        public Object boardTitle;
        public int userId;
        public String threadType;
        public String title;
        public Object content;
        public Object summary;
        public Object tags;
        public String thumbnailImageUrl;
        public String release;
        public boolean isSticky;
        public boolean isDelete;
        public boolean isComment;
        public boolean isAdopt;
        public boolean isCommentArea;
        public int pictureCount;
        public int videoCount;
        public int readCount;
        public int likeCount;
        public int reportCount;
        public int commentCount;
        public String safeStatus;
        public int startDate;
        public int endDate;
        public int surveyStartDate;
        public int surveyEndDate;
        public int responseCount;
        public BigInteger createDate;
        public BigInteger modifyDate;
        public BigInteger threadModifyDate;
        public User user;
        public Object files;
        public Object threadEmotion;
        public Object commentAuthority;
        public Object externalLink;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        public int userId;
        public String nickname;
        public int svcId;
        public String profileImageUrl;
        public boolean isAdmin;
        public Object badgeList;
        public int gradeId;
        public int gradeGroupId;
        public String userGradeDetail;
        public String userGradeStatus;
        public int createDate;
        public int lastLoginDate;
        public Object userActivity;
        public int banExpiredDate;
        public Object banInfo;
        public Object banHistory;
    }
}


