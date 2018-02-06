/*
 * JCommons
 * Copyright (C) 2018 姜永春
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jiangyc.jcommons.web;

import lombok.Data;

import java.util.List;

@Data
public class Pagination<T> extends PageRequest {
    /** 页面数量 */
    private long pageCount;
    /** 总记录数 */
    private long count;
    /** 当前页的记录 */
    private List<T> records;

    public Pagination() {
        super(1, 5);
    }

    public Pagination(PageRequest pageRequest) {
        this();
        if (pageRequest != null) {
            setPageIndex(pageRequest.getPageIndex());
            setPageSize(pageRequest.getPageSize());
        }
    }

    private Pagination(long pageIndex, long pageSize) {
        super(pageIndex, pageSize);
    }

    public void setCount(long count) {
        this.count = count;

        if (this.count <= 0) {
            this.pageCount = 0;
            return;
        } else if ((count <= 5) && (count > 0)) {
            this.pageCount = 1;
            this.setPageIndex(1);
            return;
        }

        // 计算页数
        pageCount = count % getPageSize() == 0 ? count % getPageSize() : (count % getPageSize()) + 1;
        // 设置当前页数
        if (getPageIndex() < 1) {
            setPageIndex(1);
        } else if (getPageIndex() > pageCount) {
            setPageIndex(pageCount);
        }
    }
}
