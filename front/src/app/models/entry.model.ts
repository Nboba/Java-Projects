import { Common } from "./blog.model";

export interface Entry extends Common{
    content:String,
    blogId:number
}

