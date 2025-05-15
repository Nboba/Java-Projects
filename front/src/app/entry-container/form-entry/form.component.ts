import { Component, computed, inject, input, output, signal } from '@angular/core';
import { FormControl,ReactiveFormsModule } from '@angular/forms';
import { EntryService } from '../../services/entry.service';
import { Entry } from '../../models/entry.model';

import { FloatLabelModule } from "primeng/floatlabel"
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-form-entry',
  imports: [
    ReactiveFormsModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule,
    ButtonModule,
  ],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormEntryComponent {
    private readonly entryService:EntryService=inject(EntryService);
    protected contentForm:FormControl = new FormControl<string>("")
    public blogTitle = computed(()=> this.entryService.blogTitle)


    postEntry(){
      this.entryService.postEntryBlog(this.contentForm.value).subscribe({
        next:(res)=> this.entryService.entry=res,
        error:(e)=> console.log(e),
        complete:()=> this.contentForm.setValue("")
      })
    }
}
