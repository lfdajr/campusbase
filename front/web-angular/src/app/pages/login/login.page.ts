import { Component, OnInit } from '@angular/core';
//import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../../common/service/auth.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss']
})
export class LoginPage implements OnInit {

  credentials: any = {
    username: '',
    password: ''
  };

  formGroupAcesso: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService) { }

  ngOnInit() {
    this.formGroupAcesso = this.formBuilder.group({
      username: "lourival.almeida2@gmail.com",
      password: "test1234"
    });
  }

  onSubmit() {
    if (this.formGroupAcesso.valid) {
      this.credentials.username = this.formGroupAcesso.controls['username'].value;
      this.credentials.password = this.formGroupAcesso.controls['password'].value;

      this.authService.login(this.credentials).subscribe(token => {
        if (token)
          this.router.navigate(['turmas']);
      });
    }
  }

}
