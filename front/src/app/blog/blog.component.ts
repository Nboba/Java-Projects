import { EntryService } from './../services/entry.service';
import { AfterContentInit, Component, inject, input, output, signal } from '@angular/core';
import { DatePipe } from '@angular/common';

import { Blog } from '../models/blog.model';

import { FieldsetModule } from 'primeng/fieldset';
import { ButtonModule } from 'primeng/button';
import { FormControl,ReactiveFormsModule } from '@angular/forms';
import { BlogService } from '../services/blog.service';
import { ButtonDeleteComponent } from "../buttons-save-delete/button-delete.component";
@Component({
  selector: 'app-blog',
  imports: [
    FieldsetModule,
    DatePipe,
    ButtonModule,
    ReactiveFormsModule,
    ButtonDeleteComponent,
],
  templateUrl: './blog.component.html',
  styleUrl: './blog.component.css'
})
export class BlogComponent implements AfterContentInit{
  ngAfterContentInit(): void {
    this.title.setValue(this.blog().title)
  }
  private readonly blogService = inject(BlogService);
  private readonly entryService = inject(EntryService);
  public blog=input.required<Blog>();
  protected title:FormControl = new FormControl<String>("");
  protected isEditingContent=signal(false);
  public deleteBlog= output<number>();
  public idActiveBlogInput = input<number>(-1);

  deleteBlogs(){
        this.deleteBlog.emit(this.blog().id);
  }
  patchContent(){
    this.blogService.patchBlog(this.blog().id,this.title.value)
     .subscribe({
       next:(res)=>{
           this.blog().title=res.title
           this.blog().fechaEdicion = res.fechaEdicion
           if(this.blog().id === this.entryService.blogId) this.entryService.blogTitle=res.title
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


}
