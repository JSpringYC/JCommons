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

/**
 * 分页请求
 *
 * @author jiangyc
 */
@Data
public class PageRequest {

    /** 当前页，下表从1开始计 */
    private long pageIndex;

    /** 页面大小，最小为5 */
    private long pageSize;

    /**
     * 构建一个默认的分页请求
     *
     * @return
     */
    public static PageRequest build() {
        return new PageRequest(1, 5);
    }

    /**
     * 根据给定的分页下标与分页大小构建分页请求
     *
     * @param pageIndex 分页下标，从1开始
     * @param pageSize 分页大小
     * @return 分页请求
     */
    public static PageRequest build(long pageIndex, long pageSize) {
        return new PageRequest(pageIndex, pageSize);
    }

    public PageRequest(long pageIndex, long pageSize) {
        this.pageIndex = pageIndex >= 1L ? pageIndex : 1L;
        this.pageSize = pageSize >= 5L ? pageSize : 5L;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex >= 1L ? pageIndex : 1L;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize >= 5L ? pageSize : 5L;
    }
}
