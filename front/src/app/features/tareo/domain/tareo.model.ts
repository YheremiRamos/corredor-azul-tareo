export interface TareoResumen {
  id: string;
  periodoId: string;
  areaId: string;
  estado: string;
}

export interface MatrizDia {
  id: number;
  fecha: string;
  orden: number;
  quincena: number;
  diaSemana: string;
}

export interface MatrizAsistencia {
  id: number | null;
  periodoDiaId: number;
  categoriaCodigo: string | null;
  turnoId: string | null;
  bonificacionNocturna: boolean;
  heTotal: number | null;
  he25: number | null;
  he30: number | null;
  observacion: string | null;
}

export interface MatrizFila {
  tareoColaboradorId: number;
  colaboradorId: number;
  codigo: string;
  dni: string;
  nombres: string;
  asistencias: MatrizAsistencia[];
}

export interface MatrizTareo {
  tareoId: number;
  quincena: number;
  bloqueada: boolean;
  dias: MatrizDia[];
  filas: MatrizFila[];
}

export interface GuardarAsistenciaItem {
  tareoColaboradorId: number;
  periodoDiaId: number;
  categoriaCodigo: string | null;
  turnoId: string | null;
  bonificacionNocturna: boolean;
  heTotal: number | null;
  he25: number | null;
  he30: number | null;
  observacion: string | null;
}

export const CATEGORIA_COLORS: Record<string, string> = {
  A: 'bg-emerald-100 text-emerald-800',
  F: 'bg-rose-100 text-rose-800',
  D: 'bg-sky-100 text-sky-800',
  V: 'bg-violet-100 text-violet-800',
  DM: 'bg-amber-100 text-amber-800',
  default: 'bg-slate-100 text-slate-700',
};
