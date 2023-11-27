package org.myfm.team.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyItem is a Querydsl query type for SurveyItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyItem extends EntityPathBase<SurveyItem> {

    private static final long serialVersionUID = -1702797958L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyItem surveyItem = new QSurveyItem("surveyItem");

    public final QBase _super = new QBase(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath ingredient = createString("ingredient");

    public final NumberPath<Long> listOrder = createNumber("listOrder", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath question = createString("question");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final QSurvey survey;

    public QSurveyItem(String variable) {
        this(SurveyItem.class, forVariable(variable), INITS);
    }

    public QSurveyItem(Path<? extends SurveyItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveyItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveyItem(PathMetadata metadata, PathInits inits) {
        this(SurveyItem.class, metadata, inits);
    }

    public QSurveyItem(Class<? extends SurveyItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.survey = inits.isInitialized("survey") ? new QSurvey(forProperty("survey")) : null;
    }

}

