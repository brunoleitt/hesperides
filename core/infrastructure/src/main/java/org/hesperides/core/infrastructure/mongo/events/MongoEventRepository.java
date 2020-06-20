package org.hesperides.core.infrastructure.mongo.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoEventRepository extends MongoRepository<EventDocument, String> {

    List<EventDocument> findAllByAggregateIdentifierOrderByTimestampDesc(String aggregateIdentifier, Pageable pageable);

    List<EventDocument> findAllByAggregateIdentifierAndPayloadTypeInOrderByTimestampDesc(
            String aggregateIdentifier, List<String> payloadTypes, Pageable pageable);

    void deleteAllByAggregateIdentifier(String aggregateIdentifier);

    List<EventDocument> findAllByAggregateIdentifierAndPayloadTypeAndSerializedPayloadLikeOrderByTimestampDesc(
            String aggregateIdentifier, String payloadType, String serializedPayload, Pageable pageable);

    @Query(value = "{ 'aggregateIdentifier': ?0 }", fields = "{ 'sequenceNumber': 1, 'timestamp': 1 }")
    List<EventDocument> findAllSequenceNumberAndTimestampsByAggregateIdentifier(String aggregateIdentifier);

    List<EventDocument> findAllByAggregateIdentifierAndSequenceNumberLessThanEqual(String aggregateIdentifier, int sequenceNumber);
}
