package com.andreaseisele.mussig.persistence.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class BaseObject<ID> {

    private ID id;

    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        BaseObject<?> that = (BaseObject<?>) obj;

        return id != null && id.equals(that.id);
    }

}
