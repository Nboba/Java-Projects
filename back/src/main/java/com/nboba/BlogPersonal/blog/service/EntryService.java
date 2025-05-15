package com.nboba.BlogPersonal.blog.service;

import com.nboba.BlogPersonal.blog.model.Entry;
import com.nboba.BlogPersonal.blog.projection.NoBlogEntry;

import java.util.List;

public interface EntryService {
    NoBlogEntry saveEntry(Long blogId,Entry entry);

    NoBlogEntry updateEntry(Long entryId, Entry content);

    Boolean deleteEntry(Long blogId,Long id);

    List<NoBlogEntry> getAllEntrys(Long id);
}
