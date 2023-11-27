package org.myfm.team.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResult is a Querydsl query type for Result
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResult extends EntityPathBase<Result> {

    private static final long serialVersionUID = 575720074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResult result = new QResult("result");

    public final NumberPath<Integer> ca = createNumber("ca", Integer.class);

    public final NumberPath<Integer> fe = createNumber("fe", Integer.class);

    public final QMember member;

    public final NumberPath<Integer> mg = createNumber("mg", Integer.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath survey = createString("survey");

    public final NumberPath<Integer> va = createNumber("va", Integer.class);

    public final NumberPath<Integer> vb1 = createNumber("vb1", Integer.class);

    public final NumberPath<Integer> vb2 = createNumber("vb2", Integer.class);

    public final NumberPath<Integer> vc = createNumber("vc", Integer.class);

    public final NumberPath<Integer> zn = createNumber("zn", Integer.class);

    public QResult(String variable) {
        this(Result.class, forVariable(variable), INITS);
    }

    public QResult(Path<? extends Result> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResult(PathMetadata metadata, PathInits inits) {
        this(Result.class, metadata, inits);
    }

    public QResult(Class<? extends Result> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

