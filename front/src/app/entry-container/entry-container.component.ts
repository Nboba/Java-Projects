
import {Component, inject, effect, computed } from '@angular/core';
import { EntryService } from '../services/entry.service';
import { Entry } from '../models/entry.model';
import { EntryComponent } from "../entry/entry.component";
import { FormEntryComponent } from "./form-entry/form.component";


@Component({
  selector: 'app-entry-container',
  imports: [
    EntryComponent,
    FormEntryComponent
],
  templateUrl: './entry-container.component.html',
  styleUrl: './entry-container.component.css'
})
export class EntryContainerComponent  {

  private readonly entryService:EntryService=inject(EntryService);
  protected entrys:Entry[]=[];
  public entry = computed(()=> this.entryService.entry);

  constructor() {
    effect(() => {
      this.getEntrys()
      if(this.entry().id !== -1 && this.existOnentry(this.entry()) === -1){
        this.entrys.unshift(this.entry())
      }
    });
  }

  getEntrys(){
    if(this.entryService.blogId === -1 ) return
    return this.entryService.getEntrysBlog().subscribe({
      next:(res)=>{
        this.entrys= res;
      },
      error:(e)=> console.log(e),
      complete:()=>{console.log("Completado")}
    })
  }
  deleteEntry(entryId:number):void{
    let index = this.entrys.findIndex((entry)=>{return entry?.id=== entryId})
    this.entrys.splice(index,1)
  }

  existOnentry(entry:Entry|null):number{
    return entry ===null? 0: this.entrys.findIndex((e)=>{return e?.id=== entry?.id})
  }
}
