package com.vermellosa.entities;

import com.google.common.base.Objects;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.vermellosa.utils.VersionComparatorUtil;

import java.util.List;

import static com.google.common.base.Objects.equal;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
@Entity
@Cache
public class ModelVersion extends BaseEntity implements Comparable<ModelVersion> {

    @Index
    private Integer majorVersion;

    @Index
    private Integer minorVersion;

    public ModelVersion() {
    }

    public ModelVersion(Integer majorVersion, Integer minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    public ModelVersion(long l) {
        super(l);
    }

    public static List<ModelVersion> findAll() {
        return ofy().load().type(ModelVersion.class).list();
    }

    public static ModelVersion parse(String version) {
        String[] versionMajorMinor = version.split("\\.");
        Integer majorVersion = Integer.valueOf(versionMajorMinor[0]);
        Integer minorVersion = Integer.valueOf(versionMajorMinor[1]);
        return new ModelVersion(majorVersion, minorVersion);
    }

    public String getFullVersion() {
        return majorVersion + "." + minorVersion;
    }

    public Key<ModelVersion> save() {
        return ofy().save().entity(this).now();
    }

    public Integer getMajorVersion() {
        return this.majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    public Integer getMinorVersion() {
        return this.minorVersion;
    }

    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }



    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof ModelVersion)) {
            return false;
        }
        ModelVersion that = (ModelVersion) o;
        return equal(majorVersion, that.majorVersion) &&
                equal(minorVersion, that.minorVersion);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(majorVersion, minorVersion);
    }

    @Override
    public int compareTo(ModelVersion o) {
        return VersionComparatorUtil.compare(
                this.majorVersion, this.minorVersion,
                o.majorVersion, o.minorVersion);
    }
}
