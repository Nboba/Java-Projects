import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";


export function BasicAuthInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {

    const auth = btoa("nboba:1234");

    req = req.clone({
        setHeaders: { 
            Authorization: `Basic ${auth}`
        }
    });
    return next(req);
  }