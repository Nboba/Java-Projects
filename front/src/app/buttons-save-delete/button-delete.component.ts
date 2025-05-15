import { Component, inject, input, output } from '@angular/core';

import { ConfirmationService, MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { BlogService } from '../services/blog.service';
import { EntryService } from '../services/entry.service';


@Component({
  selector: 'app-button-delete',
  imports: [ButtonModule, ToastModule, ConfirmPopupModule],
  providers: [ConfirmationService, MessageService],
  templateUrl: './button-delete.component.html',
  styleUrl: './button-delete.component.css'
})
export class ButtonDeleteComponent {

  private readonly blogService = inject(BlogService);
  private readonly entryService = inject(EntryService);
  private readonly confirmationService = inject(ConfirmationService);
  private readonly messageService = inject(MessageService);

  public type= input<string>("Blog");
  public id= input<number>(-1);
  public deleteItem= output();


  confirm2(event: Event) {
     let isBlog = this.type().localeCompare("Blog")===0;
      this.confirmationService.confirm({
          target: event.target as EventTarget,
          message: `Quieres eliminar ${isBlog?'este':'esta'} ${this.type()}`,
          icon: 'pi pi-info-circle',
          rejectButtonProps: {
              label: 'Cancelar',
              severity: 'secondary',
              outlined: true
          },
          acceptButtonProps: {
              label: 'Eliminar',
              severity: 'danger'
          },
          accept: () => {
            if(this.type().localeCompare("Blog")===0){
              this.blogService.deleteBlog(this.id()).subscribe({
                next:()=>{
                  this.messageService.add({ severity: 'info', summary: 'Eliminado', detail: 'Blog eliminado', life: 3000 });
                  setTimeout(()=> this.deleteItem.emit(),1000)
                  
                },
                error:(e)=>this.messageService.add({ severity: 'error', summary: 'Problema', detail: 'Error al Eliminar el blog', life: 3000 })
              })
            }else{
              this.entryService.deleteEntry(this.id())
              .subscribe({
                next:(res)=>{
                  this.messageService.add({ severity: 'info', summary: 'Eliminado', detail: 'Entrada eliminado', life: 3000 });
                  setTimeout(()=> this.deleteItem.emit(),1000)
                },
                error:(e)=>this.messageService.add({ severity: 'error', summary: 'Problema', detail: 'Error al Eliminar la entrada', life: 3000 })
              })
            }
            
 
          },
          reject: () => {
              
          }
      });
  }
}
