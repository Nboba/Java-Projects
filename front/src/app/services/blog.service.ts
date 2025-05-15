import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Blog } from '../models/blog.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  private readonly http:HttpClient= inject(HttpClient);
  private readonly url="http://localhost:5454/blog"
  private readonly _blog = signal<Blog>({
    "id": -1,
    "title": "",
    "fechaCreacion": new Date,
    "fechaEdicion": new Date
  });


    public getBlogs():Observable<Blog[]>{
      return this.http.get<Blog[]>(this.url);
    }
    public deleteBlog(blogId:number):Observable<any>{
      return this.http.delete(this.url+`/${blogId}`);
    }
    public postBlog(title:string):Observable<Blog>{
      return this.http.post<Blog>(this.url,{"title":title});
    }
  
    public patchBlog(blogId:number,title:String):Observable<Blog>{
      return this.http.patch<Blog>(this.url+`/${blogId}`,{"title":title});
    }
    public get blog():Blog {
      return this._blog();
    }
    public set blog(blog:Blog){
      this._blog.update(()=> blog);
    }
}
