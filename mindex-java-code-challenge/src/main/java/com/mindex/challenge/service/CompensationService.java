package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import java.util.List;
/**
 *
 * @author Liam
 */
public interface CompensationService {
    Compensation create(Compensation compensation);
    List<Compensation> read(String id);
}