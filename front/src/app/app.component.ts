import { EntryContainerComponent } from './entry-container/entry-container.component';
import { Component } from '@angular/core';
import { BlogContainerComponent } from './blog-container/blog-container.component';
import { PanelModule } from 'primeng/panel';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { FormBlogComponent } from "./blog-container/form-blog/form.component";
import { FormEntryComponent } from "./entry-container/form-entry/form.component";

@Component({
  selector: 'app-root',
  imports: [
    BlogContainerComponent,
    EntryContainerComponent,
    PanelModule,
    ScrollPanelModule,
    FormBlogComponent,
    FormEntryComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'blog-personal';
}
