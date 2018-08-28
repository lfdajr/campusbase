import { Usuario } from "./usuario";
import { Curso } from "./curso";

export class Matricula {
    matriculaPK: any;
    dataHoraCriacao: string;
    situacao: string;
    codigo: string;
    curso: Curso;
    usuario: Usuario;
}