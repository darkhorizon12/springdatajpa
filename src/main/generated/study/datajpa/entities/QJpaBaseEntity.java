package study.datajpa.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaBaseEntity is a Querydsl query type for JpaBaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QJpaBaseEntity extends EntityPathBase<JpaBaseEntity> {

    private static final long serialVersionUID = 90009636L;

    public static final QJpaBaseEntity jpaBaseEntity = new QJpaBaseEntity("jpaBaseEntity");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public QJpaBaseEntity(String variable) {
        super(JpaBaseEntity.class, forVariable(variable));
    }

    public QJpaBaseEntity(Path<? extends JpaBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaBaseEntity(PathMetadata metadata) {
        super(JpaBaseEntity.class, metadata);
    }

}

