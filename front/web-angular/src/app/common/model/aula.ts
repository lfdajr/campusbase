import  {Midia} from './midia';
import { Curso } from './curso';

export class Aula {
    id: number;
    titulo: string;
    descricao: string;
    dataHora: string;
    dataHoraCriacao: string;
    status: string;
    arquivos: Midia[];
    curso: Curso;
}