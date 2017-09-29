package org.fan.dataaccess.api;

public interface PageQueryFactory<T extends IPageQuery> {

	T getPageQuery();

	T getPageQuery(Page page, String queryStr, String countStr);

}
