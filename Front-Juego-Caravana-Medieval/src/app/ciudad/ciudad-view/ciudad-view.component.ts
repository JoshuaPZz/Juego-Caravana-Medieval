import { Component, OnInit } from '@angular/core';
import { CiudadDto } from '../../dto/ciudad-dto';
import { CiudadService } from '../ciudad.service';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ciudad-view',
  imports: [CommonModule, RouterModule],
  templateUrl: './ciudad-view.component.html',
  styleUrls: ['./ciudad-view.component.css'],
})
export class CiudadViewComponent implements OnInit {
  parametroCiudad: CiudadDto | undefined;

  constructor(
    private ciudadService: CiudadService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;

    this.ciudadService.getCiudadById(id).subscribe((ciudad) => {
      this.parametroCiudad = ciudad;
      console.log('Ciudad loaded:', this.parametroCiudad);
    });
  }

  back(): void {
    this.router.navigate(['/ciudades/list']);
  }
}
