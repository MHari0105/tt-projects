import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOninit(): void {
    this.loginForm = this.formBuilder.group({
      email : ['', [Validators.email, Validators.required]],
      password: ['', [Validators.required]]
    });
  }

}
