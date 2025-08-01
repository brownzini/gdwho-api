package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.github.fge.jsonpatch.JsonPatch;

public interface GameGateway {
    void   createGame(String response, List<String> dataList, List<EntriesDomainEntity> entries, Long userId);
    double guessResult(String input, Long userId);
    void   dataUpdate(Long dataId, String value);
    void   entrieUpdate(Long entriesId, JsonPatch patch);
    void   deleteData(Long dataId);
    void   deleteEntrie(Long entrieId);
}
