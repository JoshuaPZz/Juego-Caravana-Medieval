import { Component } from '@angular/core';
import { LoginDto } from '../../dto/login-dto';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../../servicios/auth-services/auth-service.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private auth : AuthServiceService, private router : Router) {}
  loginDto: LoginDto = new LoginDto("", "")

  ngOnInit() : void {
    this.auth.logout
  }

  login() {
    this.auth.login(this.loginDto)
      .subscribe({
        next: jwt => {
          console.log(jwt);
          this.router.navigate(["home"]);
        },
        error : err => {console.error("Login failed: ", err)}
      })
  }

}
