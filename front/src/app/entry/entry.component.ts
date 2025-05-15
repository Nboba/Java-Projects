import { PanelModule } from 'primeng/panel';
import { AfterContentInit, Component, inject, input, output, signal } from '@angular/core';
import { DatePipe } from '@angular/common';

import { Entry } from '../models/entry.model';
import { EntryService } from '../services/entry.service';

import { AvatarModule } from 'primeng/avatar';
import { ButtonModule } from 'primeng/button';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { TextareaModule } from 'primeng/textarea';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { ButtonDeleteComponent } from "../buttons-save-delete/button-delete.component";

@Component({
  selector: 'app-entry',
  imports: [
    PanelModule,
    AvatarModule,
    DatePipe,
    ButtonModule,
    ReactiveFormsModule,
    TextareaModule,
    ScrollPanelModule,
    ButtonDeleteComponent
],
  templateUrl: './entry.component.html',
  styleUrl: './entry.component.css'
})
export class EntryComponent implements AfterContentInit {
  private readonly entrySesion=inject(EntryService)
  public inputEntry= input.required<Entry>();
  protected entry= signal<Entry>({
    content: "",
    blogId: 0,
    id: 0,
    fechaCreacion: new Date,
    fechaEdicion: new Date
  });
  protected content:FormControl = new FormControl<String>("");
  protected isEditingContent=signal(false);
  public isEntryDeleted= output<number>()

  deleteEntry(){
    this.entrySesion.deleteEntry(this.entry().id)
    .subscribe({
      next:(res)=>{
        this.isEntryDeleted.emit(this.entry().id)
        console.log(res)},
      error:(e)=>console.log(e)
    })
  }
  patchContent(){
   this.entrySesion.patchEntryBlog(this.entry().id,this.content.value)
    .subscribe({
      next:(res)=>{
          this.entry().content=res.content
          this.entry().fechaEdicion = res.fechaEdicion
          this.cancelPatch()},
      error:(e)=>console.log(e)
    }) 
  }
  activeEditContent(){
    this.isEditingContent.update(() => !this.isEditingContent());
  }
  cancelPatch(){
    this.isEditingContent.update(() => false);
  }

  ngAfterContentInit(): void {
    this.entry.set(this.inputEntry())
    this.content.setValue(this.entry().content);
  }
}
