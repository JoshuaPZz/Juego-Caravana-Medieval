import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthServiceService } from '../servicios/auth-services/auth-service.service';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../popup/popup.component';

export const authGuard: CanActivateFn = (route, state) => {
  let authService = inject(AuthServiceService);
  let router = inject(Router);

  if (authService.isAuthenticated()) {
    return true;
  } else {
    router.navigateByUrl('/login');
    return false;
  }
};

export const authGuardCaravanero: CanActivateFn = (route, state) => {
  let authService = inject(AuthServiceService);
  let router = inject(Router);
  let dialog = inject(MatDialog);

  if (authService.isAuthenticated() && authService.role()=='CARAVANERO') {
    return true;
  } else {
    dialog.open(PopupComponent, {
      data: { message: 'No puede ingresar a esa URL' },
    });
    router.navigateByUrl('/inicio');
    return false;
  }
}

export const authGuardComerciante: CanActivateFn = (route, state) => {
  let authService = inject(AuthServiceService);
  let router = inject(Router);
  let dialog = inject(MatDialog);

  if (authService.isAuthenticated() && authService.role()=='COMERCIANTE') {
    return true;
  } else {
    dialog.open(PopupComponent, {
      data: { message: 'No puede ingresar a esa URL' },
    });
    router.navigateByUrl('/inicio');
    return false;
  }
}
