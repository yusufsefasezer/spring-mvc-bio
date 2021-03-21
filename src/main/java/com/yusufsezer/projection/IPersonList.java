package com.yusufsezer.projection;

public interface IPersonList extends IViewInfo {

    String getDescription();

    String getPhoto();

    String getSlug();

    default String link() {
        return getSlug() + "-" + getId();
    }

}
