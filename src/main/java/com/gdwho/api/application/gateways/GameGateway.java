package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.github.fge.jsonpatch.JsonPatch;

public interface GameGateway {
    void   createGame(String response, List<String> dataList, List<EntriesDomainEntity> entries, Long userId);
    double guessResult(String input, Long userId);
    void   dataUpdate(Long dataId, Long userId, String value);
    void   entrieUpdate(Long entriesId, Long userId, JsonPatch patch);
    void   deleteData(Long dataId, Long userId);
    void   deleteEntrie(Long entrieId, Long userId);
}
