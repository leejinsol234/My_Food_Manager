package org.myfm.team.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPublicDB is a Querydsl query type for PublicDB
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublicDB extends EntityPathBase<PublicDB> {

    private static final long serialVersionUID = 845602772L;

    public static final QPublicDB publicDB = new QPublicDB("publicDB");

    public final QBase _super = new QBase(this);

    public final NumberPath<Double> ca = createNumber("ca", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Double> fe = createNumber("fe", Double.class);

    public final StringPath foodNm = createString("foodNm");

    public final NumberPath<Double> mg = createNumber("mg", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> nuNo = createNumber("nuNo", Long.class);

    public final NumberPath<Double> va = createNumber("va", Double.class);

    public final NumberPath<Double> vb1 = createNumber("vb1", Double.class);

    public final NumberPath<Double> vb2 = createNumber("vb2", Double.class);

    public final NumberPath<Double> vc = createNumber("vc", Double.class);

    public final NumberPath<Double> zn = createNumber("zn", Double.class);

    public QPublicDB(String variable) {
        super(PublicDB.class, forVariable(variable));
    }

    public QPublicDB(Path<? extends PublicDB> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPublicDB(PathMetadata metadata) {
        super(PublicDB.class, metadata);
    }

}

