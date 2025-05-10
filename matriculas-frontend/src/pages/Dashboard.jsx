import { useEffect, useState } from 'react';
import {
  getPorAnoModalidade,
  getPorAnoEstado,
  getPorAnoEstadoModalidade,
  getTotalPorAno,
  getTopCursos2022Modalidade,
  getTopCursosEstadoModalidade
} from '../services/api';

import GraficoLinhasAno from '../components/GraficoLinhasAno';
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid
} from 'recharts';

import { useNavigate } from 'react-router-dom';

export default function Dashboard() {
  const [estado, setEstado] = useState('');
  const [modalidade, setModalidade] = useState('');
  const [dadosAno, setDadosAno] = useState([]);
  const [dadosRankingModalidade, setDadosRankingModalidade] = useState([]);
  const [dadosRankingEstadoModalidade, setDadosRankingEstadoModalidade] = useState([]);
  const [historico, setHistorico] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    aplicarConsulta();
  }, []);

  const aplicarConsulta = async (filtroCustomizado = null) => {
    let dados = [];
    let filtro = filtroCustomizado || { estado, modalidade };

    if (filtro.estado && filtro.modalidade) {
      dados = (await getPorAnoEstadoModalidade(filtro.estado, filtro.modalidade)).data;
    } else if (filtro.estado) {
      dados = (await getPorAnoEstado(filtro.estado)).data;
    } else if (filtro.modalidade) {
      dados = (await getPorAnoModalidade(filtro.modalidade)).data;
    } else {
      dados = (await getTotalPorAno()).data;
    }

    const ranking1 = (await getTopCursos2022Modalidade(filtro.modalidade || 'EaD')).data;
    const ranking2 = (await getTopCursosEstadoModalidade(filtro.estado || 'RIO GRANDE DO SUL', filtro.modalidade || 'EaD')).data;

    setDadosAno(dados);
    setDadosRankingModalidade(ranking1);
    setDadosRankingEstadoModalidade(ranking2);

    if (!filtroCustomizado) {
      const novaConsulta = { ...filtro, dataHora: new Date().toISOString() };
      setHistorico(prev => [...prev, novaConsulta]);
    }
  };

  const limparHistorico = () => setHistorico([]);

  const estados = [
    "SÃO PAULO", "RIO DE JANEIRO", "SANTA CATARINA", "MINAS GERAIS", "GOIÁS",
    "CEARÁ", "DISTRITO FEDERAL", "RIO GRANDE DO NORTE", "PERNAMBUCO", "SERGIPE",
    "PARANÁ", "RIO GRANDE DO SUL", "MARANHÃO", "PARÁ", "BAHIA", "AMAZONAS",
    "PIAUÍ", "MATO GROSSO", "PARAÍBA", "ALAGOAS", "RORAIMA", "AMAPÁ", "RONDÔNIA",
    "MATO GROSSO DO SUL", "ESPÍRITO SANTO", "ACRE", "TOCANTINS"
  ];

  return (
    <div className="container">
      <h1 className="titulo">📊 Dashboard de Matrículas</h1>

      <button className="botao-voltar" onClick={() => navigate('/')}>🔙 Voltar ao Menu</button>

      <div className="filtros">
        <div>
          <label>Estado:</label>
          <select value={estado} onChange={(e) => setEstado(e.target.value)}>
            <option value="">Todos</option>
            {estados.map((uf) => (
              <option key={uf} value={uf}>{uf}</option>
            ))}
          </select>
        </div>

        <div>
          <label>Modalidade:</label>
          <select value={modalidade} onChange={(e) => setModalidade(e.target.value)}>
            <option value="">Todas</option>
            <option value="EaD">EaD</option>
            <option value="Presencial">Presencial</option>
          </select>
        </div>

        <button className="botao-primario" onClick={() => aplicarConsulta()}>🔎 Aplicar Filtros</button>
        <button className="botao-secundario" onClick={limparHistorico}>🗑️ Limpar Histórico</button>
      </div>

      <section>
        <h2>Total de Matrículas por Ano</h2>
        <GraficoLinhasAno dados={dadosAno} />
      </section>

      <section>
        <h2>Ranking por Modalidade</h2>
        <ResponsiveContainer width="100%" height={300}>
          <BarChart data={dadosRankingModalidade}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis 
              className="texto-grafico"
              dataKey="chave" 
              angle={-20} 
              textAnchor="end" 
              interval={0} 
              height={100}/>
            <YAxis />
            <Tooltip />
            <Bar dataKey="total" fill="#F7A4B8" />
          </BarChart>
        </ResponsiveContainer>
      </section>

      <section>
        <h2>Ranking por Estado e Modalidade</h2>
        <ResponsiveContainer width="100%" height={300}>
          <BarChart data={dadosRankingEstadoModalidade}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis 
              className="texto-grafico"
              dataKey="chave" 
              angle={-20} 
              textAnchor="end" 
              interval={0} 
              height={100}/>
            <YAxis />
            <Tooltip />
            <Bar dataKey="total" fill="#EC5E8F" />
          </BarChart>
        </ResponsiveContainer>
      </section>

      <section>
        <h2>Histórico de Consultas</h2>
        {historico.length === 0 ? (
          <p className="texto-vazio">Nenhuma consulta aplicada ainda.</p>
        ) : (
          <table className="tabela-historico">
            <thead>
              <tr>
                <th>#</th>
                <th>Estado</th>
                <th>Modalidade</th>
                <th>Data/Hora</th>
                <th>Ação</th>
              </tr>
            </thead>
            <tbody>
              {historico.map((h, i) => (
                <tr key={i}>
                  <td>{i + 1}</td>
                  <td>{h.estado || 'Todos'}</td>
                  <td>{h.modalidade || 'Todas'}</td>
                  <td>{new Date(h.dataHora).toLocaleString('pt-BR')}</td>
                  <td>
                    <button onClick={() => aplicarConsulta({ estado: h.estado || '', modalidade: h.modalidade || '' })}>
                      Reaplicar
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>
    </div>
  );
}
