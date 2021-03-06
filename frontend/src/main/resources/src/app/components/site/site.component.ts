import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'site-component',
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.scss']
})
export class SiteComponent implements OnInit {


  mostrarLogin: boolean;
  mostrarCadastroUsuario: boolean;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser) {
      this.router.navigate(['dashboard']);
    }
    this.mostrarCadastroUsuario = false;
    this.mostrarLogin = false;
  }

  ngOnInit() {
    //this.ngxService.start();
  }

}
