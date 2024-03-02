import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';

@NgModule({
  exports: [
    MatToolbarModule,
    MatFormFieldModule
  ]
})
export class MaterialModule { }
