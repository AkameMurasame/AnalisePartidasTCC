import { Component, Inject, ViewChild } from '@angular/core';
import { LoginComponent } from './modules/usuario/components/login/login.component';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  
  animal: string;
  name: string;

  constructor() { }

  
}

