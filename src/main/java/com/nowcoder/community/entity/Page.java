package com.nowcoder.community.entity;

/**
 * 封装分页相关的信息
 * @author QiaoWeiBo
 * @date 2022/6/19 8:22 PM
 */
public class Page {

    //当前页码
    private int current = 1;
    //一个页面的显示上限
    private int limit = 10;
    //数据总数{用于计算总的页数}
    private int rows;
    //查询路径{用于复用分页的链接}
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的「起始行」
     * @author QiaoWeiBo
     * @date 2022/6/19 8:33 PM
     */
    public int getOffset() {
        //current * limit - limit
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     * @author QiaoWeiBo
     * @date 2022/6/19 8:35 PM
     */
    public int getTotal() {
        //rows / limit [+1]
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }

    /**
     * 获取起始页面
     * @author QiaoWeiBo
     * @date 2022/6/19 8:38 PM
     */
    public int getFrom() {
        int from = current - 2;
       return from < 1 ? 1 : from;
    }

    /**
     * 获取结束页码
     * @author QiaoWeiBo
     * @date 2022/6/19 8:40 PM
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
