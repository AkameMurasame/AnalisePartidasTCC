import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  isLogado: boolean;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
    this.isLogado = false;
  }

  ngOnInit() {
  }

  deslogar() {
    console.log('321');
    this.authenticationService.logout();
    this.router.navigate(['/']);
  }

}
