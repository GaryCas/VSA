package com.vermellosa.repositories;

import com.vermellosa.entities.ModelVersion;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public class ModelVersionRepository extends BaseRepository<ModelVersion> {

    public ModelVersion findLatestDatastoreModelVersion() {
        List<ModelVersion> versions =
                ofy()
                        .load()
                        .type(getType())
                        .order("-majorVersion")
                        .order("-minorVersion")
                        .limit(1)
                        .list();

        if (versions != null && !versions.isEmpty()) {
            return versions.get(0);
        }

        return null;
    }

    public ModelVersion findByDataModelVersion(String majorMinorVersion) {
        return ofy().load().type(getType()).id(majorMinorVersion).now();
    }


    @Override
    public Class<ModelVersion> getType() {
        return ModelVersion.class;
    }
}
