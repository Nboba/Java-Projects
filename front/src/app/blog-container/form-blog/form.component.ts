import { Component, inject, output } from '@angular/core';
import { FormControl,ReactiveFormsModule } from '@angular/forms';
import { BlogService } from '../../services/blog.service';
import { Blog } from '../../models/blog.model';

import { FloatLabelModule } from "primeng/floatlabel"
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-form-blog',
  imports: [
    ReactiveFormsModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule,
    ButtonModule
  ],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormBlogComponent {
  private readonly blogSerivce= inject(BlogService);
  protected inputTitle:FormControl= new FormControl<string>("");

  postBlog(){
    this.blogSerivce.postBlog(this.inputTitle.value).subscribe({
      next:(res)=> this.blogSerivce.blog=res,
      error:(e)=> console.log(e),
      complete:()=> this.inputTitle.setValue("")
    })
  }
}
