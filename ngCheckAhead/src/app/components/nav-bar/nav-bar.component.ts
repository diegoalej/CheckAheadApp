import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isCollapsed = false;

  constructor(
     private auth: AuthService
  ) { }

  ngOnInit(): void {
  }

  checkLogin(): boolean {
    return this.auth.checkLogin();
  }
}
