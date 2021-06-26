
function tablero(m, n) {
    this.cambiarEstado = (i, j) => {
        this.estado[i][j] = !this.estado[i][j];
        if (this.estado[i][j]) {
            this.k++;
        } else {
            this.k--;
        }
    };

    this.reiniciar = () => {
        this.estado = Array(m).fill(null).map(x => Array(n).fill(false));
        this.k = 0;
    };

    this.m = () => {
        return this.estado.length;
    };

    this.n = () => {
        return this.estado[0].length;
    };

    this.estado = Array(m).fill(null).map(x => Array(n).fill(false));
    this.k = 0;
}
