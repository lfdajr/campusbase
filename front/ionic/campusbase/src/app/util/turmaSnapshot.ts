import { Matricula } from "../../model/matricula";

export class TurmaSnapshot {
    
    turmaInfo;

    constructor() {
        let info = localStorage.getItem("turmas");
        this.turmaInfo = {};
        if (info !== null && info !== "")
        {
            let t : TurmaInfo[] = JSON.parse(info);
            t.forEach(e => {
                this.turmaInfo[e.id.toString()] = e;
            });
        }
    }

    private salvarInLocalStorage() {
        let obj = Object.keys(this.turmaInfo).map(k => {
            console.log(this.turmaInfo[k]);
            return new TurmaInfo(this.turmaInfo[k].id, this.turmaInfo[k].novidade)
        });

        localStorage.setItem("turmas", JSON.stringify(obj));
    }

    compare(matriculas: Matricula[]) {
        if (matriculas!=null && matriculas.length > 0)
        {
            matriculas.forEach(mat => {
                if (this.turmaInfo[mat.curso.id.toString()])
                    mat.showNovidade = this.turmaInfo[mat.curso.id.toString()].novidade != mat.curso.ordenacao;
                else
                    mat.showNovidade = false;
            });
        }
    }

    update(matricula: Matricula) {
        console.log("update");
        
        if (this.turmaInfo[matricula.curso.id.toString()])
            this.turmaInfo[matricula.curso.id.toString()].novidade = matricula.curso.ordenacao;
        else {
            console.log(matricula);
            this.turmaInfo[matricula.curso.id.toString()] = new TurmaInfo(matricula.curso.id, matricula.curso.ordenacao);
        }
        this.salvarInLocalStorage();
    }

}

class TurmaInfo {
    id: number;
    novidade: number;

    constructor (id: number, novidade: number) {
        this.id = id;
        this.novidade = novidade;
    }
}