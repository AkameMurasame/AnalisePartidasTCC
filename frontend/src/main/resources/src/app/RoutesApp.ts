import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';
import { SiteComponent } from './components/site/site.component';
import { DashboardComponent } from './modules/usuario/components/dashboard/dashboard.component';

const appRoutes: Routes = [
    { path: '', component: SiteComponent },
    { path: 'dashboard', component: DashboardComponent ,  canActivate: [AuthGuard]},
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);