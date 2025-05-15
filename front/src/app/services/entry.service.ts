import { Entry } from './../models/entry.model';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  private readonly http:HttpClient= inject(HttpClient);
  private readonly url="http://localhost:5454/entry";
  private _blogId= signal<number>(-1);
  private _blogTitle=signal<String>("");
  private _newEntry = signal<Entry>({"id":-1,
                                      "content":"",
                                      "blogId":-1,
                                      "fechaCreacion":new Date,
                                      "fechaEdicion":new Date});
 
  public getEntrysBlog():Observable<Entry[]>{
    return this.http.get<Entry[]>(this.url+`/${this.blogId}`);
  }
  public deleteEntry(entryId:number):Observable<any>{
    return this.http.delete(this.url+`/${this.blogId}/${entryId}`);
  }
  public postEntryBlog(content:string):Observable<Entry>{
    return this.http.post<Entry>(this.url+`/${this.blogId}`,{"content":content});
  }

  public patchEntryBlog(entryId:number,content:String):Observable<Entry>{
    return this.http.patch<Entry>(this.url+`/${entryId}`,{"content":content});
  }

  public set blogId(blogId:number){
    this._blogId.update(()=> blogId);
  }
  public get blogId():number{
    return this._blogId();
  }
  public set blogTitle(blogTitle:String){
    this._blogTitle.update(()=> blogTitle);
  }
  public get blogTitle():String{
    return this._blogTitle();
  }
  public get entry():Entry {
    return this._newEntry();
  }
  public set entry(value:Entry) {
    this._newEntry.set(value);
  }
}
