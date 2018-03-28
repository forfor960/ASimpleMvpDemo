package com.yingzi.common.model;

import java.util.List;

/**
 * Created by yingzi on 2017/5/7.
 *     "curPage": 1,
 "list": [
 {
 "cid": "1",
 "id": "2433405",
 "pubTime": "2016-02-2923:44",
 "sourceUrl": "http://mp.weixin.qq.com/s?__biz=MzA4MDQ1NTAyMQ==&mid=402016356&idx=2&sn=d43f4f07393814e344edffe608ac6619&scene=4#wechat_redirect",
 "subTitle": "你想要的百褶裙，这里一定有~",
 "title": "春日里摇曳裙摆，那些好看的百褶裙哪里买？"
 },
 */
public class DataModel {
    private String msg;
    private String retCode;
    private Result result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private String  curPage;
        private List<Detail> list;

        public String getCurPage() {
            return curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public List<Detail> getList() {
            return list;
        }

        public void setList(List<Detail> list) {
            this.list = list;
        }
    }

    public class Detail {
        private String cid;
        private String id;
        private String pubTime;
        private String sourceUrl;
        private String subTitle;
        private String title;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
