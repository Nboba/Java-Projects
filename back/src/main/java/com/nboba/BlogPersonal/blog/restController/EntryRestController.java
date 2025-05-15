package com.nboba.BlogPersonal.blog.restController;


import com.nboba.BlogPersonal.blog.model.Entry;
import com.nboba.BlogPersonal.blog.projection.NoBlogEntry;
import com.nboba.BlogPersonal.blog.service.EntryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryRestController {
    private EntryServiceImpl entryService;

    public EntryRestController(EntryServiceImpl entryService){
        this.entryService=entryService;
    }

    @PostMapping("{blogId}")
    public ResponseEntity<?> saveEntry( @PathVariable("blogId") Long blogId,@RequestBody Entry entry){
        NoBlogEntry entryLo =entryService.saveEntry(blogId,entry);
        if(entryLo!=null) return new ResponseEntity<>(entryLo, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("{entryId}")
    public ResponseEntity<?> updateEntry(@PathVariable("entryId") Long entryId,
                                          @RequestBody Entry content){
        NoBlogEntry entryLo =entryService.updateEntry(entryId,content);
        if(entryLo!=null) {
            return new ResponseEntity<>(entryLo, HttpStatus.OK);
        };
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{blogId}/{entryId}")
    public ResponseEntity<?> deleteEntry(@PathVariable("blogId") Long blogId,@PathVariable("entryId") Long entryId){
        if(entryService.deleteEntry(blogId,entryId)) return new ResponseEntity<>(HttpStatus.OK) ;
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("{blogId}")
    public ResponseEntity<?> getEntrys(@PathVariable("blogId") Long blogId){
        List<NoBlogEntry> entrys= entryService.getAllEntrys(blogId);
        if(entrys == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(entrys, HttpStatus.OK);
    }
}
