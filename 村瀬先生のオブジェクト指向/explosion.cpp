//explotion.cpp

#include "main.h"
#include "renderer.h"
#include "explosion.h"
#include "camera.h"
#include "manager.h"
#include "scene.h"

void CExplosion::Init()
{
	m_Count = 0;

	m_Position = D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Rotation = D3DXVECTOR3(0.0f, 0.0f, 0.0f);
	m_Scale    = D3DXVECTOR3(1.0f, 1.0f, 1.0f);
}

void CExplosion::Uninit()
{

}

void CExplosion::Update()
{
	m_Count++;

	if (m_Count >= 16)
	{
		SetDestroy();
		return;
	}
}

void CExplosion::Draw()
{
	//ライト処理(消してもいい)	加算合成いじってみるのもいい

	float x = m_Count % 4 * (1.0f / 4);
	float y = m_Count / 4 * (1.0f / 4);

	D3D11_MAPPED_SUBRESOURCE msr;
	CRenderer::GetDeviceContext()->Map(m_VertexBuffer,0,D3D11_MAP_WRITE_DISCARD,0,&msr);

	VERTEX_3D* vertex = (VERTEX_3D*)msr.pData;

	vertex[0].Position = D3DXVECTOR3(-1.0f, 1.0f, 0.0f);
	vertex[0].Normal   = D3DXVECTOR3(0.0f,1.0f,0.0f);
	vertex[0].Diffuse  = D3DXVECTOR4(1.0f,1.0f,1.0f,1.0f);
	vertex[0].TexCoord = D3DXVECTOR2(x,y);

	vertex[1].Position = D3DXVECTOR3(1.0f,1.0f,0.0f);
	vertex[1].Normal   = D3DXVECTOR3(0.0f,1.0f,0.0f);
	vertex[1].Diffuse  = D3DXVECTOR4(1.0f,1.0f,1.0f,1.0f);
	vertex[1].TexCoord = D3DXVECTOR2(x + (1.0f / 4),y);

	vertex[2].Position = D3DXVECTOR3(-1.0f, -1.0f, 0.0f);
	vertex[2].Normal   = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[2].Diffuse  = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[2].TexCoord = D3DXVECTOR2(x, y + (1.0f / 4));

	vertex[3].Position = D3DXVECTOR3(1.0f, -1.0f, 0.0f);
	vertex[3].Normal   = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
	vertex[3].Diffuse  = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
	vertex[3].TexCoord = D3DXVECTOR2(x + (1.0f / 4), y + (1.0f / 4));

	CRenderer::GetDeviceContext()->Unmap(m_VertexBuffer,0);

	//ビルボード処理
	CCamera* camera = CManager::GetScene()->GetGameObject<CCamera>(LAYER_CAMERA);
	D3DXMATRIX view = camera->GetViewMatrix();
	D3DXMATRIX invView;
	D3DXMatrixInverse(&invView, NULL, &view);	//逆行列
	invView._41 = 0.0f;
	invView._42 = 0.0f;
	invView._43 = 0.0f;

	//マトリクス設定
	D3DXMATRIX world, scale, trans;
	D3DXMatrixScaling(&scale, m_Scale.x, m_Scale.y, m_Scale.z);
	D3DXMatrixTranslation(&trans, m_Position.x, m_Position.y, m_Position.z);
	world = scale * invView * trans;
	CRenderer::SetWorldMatrix(&world);

	//頂点バッファ設定
	UINT stride = sizeof(VERTEX_3D);
	UINT offset = 0;
	CRenderer::GetDeviceContext()->IASetVertexBuffers(
		0, 1, &m_VertexBuffer, &stride, &offset);

	//マテリアル設定
	MATERIAL material;
	ZeroMemory(&material, sizeof(material));
	material.Diffuse = D3DXCOLOR(1.0f, 1.0f, 1.0f, 1.0f);
	CRenderer::SetMaterial(material);

	//テクスチャ設定
	CRenderer::GetDeviceContext()->PSSetShaderResources(0, 1, &m_Texture);

	//プリミティブトポロジ設定
	CRenderer::GetDeviceContext()->IASetPrimitiveTopology(
		D3D11_PRIMITIVE_TOPOLOGY_TRIANGLESTRIP);

	//ポリゴン描画
	CRenderer::GetDeviceContext()->Draw(4, 0);
}

void CExplosion::Load()
{
	VERTEX_3D vertex[4];
	{
		vertex[0].Position = D3DXVECTOR3(-1.0f, 1.0f, 0.0f);
		vertex[0].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[0].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[0].TexCoord = D3DXVECTOR2(0.0f, 0.0f);

		vertex[1].Position = D3DXVECTOR3(1.0f, 1.0f, 0.0f);
		vertex[1].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[1].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[1].TexCoord = D3DXVECTOR2(10.0f, 0.0f);

		vertex[2].Position = D3DXVECTOR3(-1.0f, -1.0f, 0.0f);
		vertex[2].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[2].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[2].TexCoord = D3DXVECTOR2(0.0f, 10.0f);

		vertex[3].Position = D3DXVECTOR3(1.0f, -1.0f, 0.0f);
		vertex[3].Normal = D3DXVECTOR3(0.0f, 1.0f, 0.0f);
		vertex[3].Diffuse = D3DXVECTOR4(1.0f, 1.0f, 1.0f, 1.0f);
		vertex[3].TexCoord = D3DXVECTOR2(10.0f, 10.0f);
	}

	//頂点バッファ生成
	D3D11_BUFFER_DESC bd;
	ZeroMemory(&bd, sizeof(bd));
	bd.Usage = D3D11_USAGE_DYNAMIC;
	bd.ByteWidth = sizeof(VERTEX_3D) * 4;
	bd.BindFlags = D3D11_BIND_VERTEX_BUFFER;
	bd.CPUAccessFlags = D3D11_CPU_ACCESS_WRITE;

	D3D11_SUBRESOURCE_DATA sd;
	ZeroMemory(&sd, sizeof(sd));
	sd.pSysMem = vertex;

	CRenderer::GetDevice()->CreateBuffer(&bd, &sd, &m_VertexBuffer);

	//テクスチャ読み込み
	D3DX11CreateShaderResourceViewFromFile(CRenderer::GetDevice(),
		"asset/texture/explosion.png",
		NULL,
		NULL,
		&m_Texture,
		NULL);

	//エラー通知でデバックを簡単に
	assert(m_Texture);
}

void CExplosion::Unload()
{
	m_VertexBuffer->Release();
	m_Texture->Release();
}

