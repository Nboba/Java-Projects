import { EntryService } from './../services/entry.service';
import { Component, computed, effect, inject, OnInit, signal } from '@angular/core';

import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { ScrollPanelModule } from 'primeng/scrollpanel';


import { BlogComponent } from "../blog/blog.component";
import { BlogService } from '../services/blog.service';
import { Blog } from '../models/blog.model';
import { FormBlogComponent } from "./form-blog/form.component";
import { FormEntryComponent } from "../entry-container/form-entry/form.component";


@Component({
  selector: 'app-blog-container',
  imports: [
    BlogComponent,
    PanelModule,
    ButtonModule,
    MenuModule,
    ScrollPanelModule,
    FormBlogComponent,
    FormEntryComponent
],
  templateUrl: './blog-container.component.html',
  styleUrl: './blog-container.component.css'
})
export class BlogContainerComponent implements OnInit {
  private readonly blogService = inject(BlogService);
  private readonly entryService=inject(EntryService)
  protected blogs= signal<Blog[]>([]);
  private readonly blog= computed(()=> this.blogService.blog)

  constructor(){
    effect(()=>{
      if(this.blog().id !== -1){
          this.blogs().unshift(this.blog())
      }
    })
  }
  ngOnInit(): void {
    this.blogService.getBlogs().subscribe({
      next:(res)=>{
        this.blogs.set(res);
        this.entryService.blogId=this.blogs()[0].id
        this.entryService.blogTitle= this.blogs()[0].title
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete') 
    });
  } 
  deleteBlog(blogid:number){
        this.blogs().splice(this.blogs().findIndex((blog)=> blog.id===blogid),1);
  }
  changeBlogClick(blogid:number,blogTitle:String):void{
    this.entryService.blogId=blogid;
    this.entryService.blogTitle=blogTitle
  }
  getActiveBlog(){
    return this.entryService.blogId;
  }
}
